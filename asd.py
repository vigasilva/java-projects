import pygame
import math
import threading
import time

# Initialize Pygame
pygame.init()

# Set up the display
width, height = 800, 600
screen = pygame.display.set_mode((width, height))
pygame.display.set_caption("Rainbow Color Changing Band with Threading")

# Set up clock for FPS control
clock = pygame.time.Clock()

# Band dimensions
band_width = 800
band_height = 50
band_x = 0
band_y = (height - band_height) // 2

# Time-related variables
start_time = pygame.time.get_ticks()  # Start time to track elapsed time

# Global variable to store the hue
hue = 0

# Function to get color from a hue value (0 to 1) using HSL to RGB conversion
def hue_to_rgb(hue):
    # Ensure hue is between 0 and 1
    hue = hue % 1
    r, g, b = [0] * 3
    
    if hue < 1/6:
        r = 1
        g = 6 * hue
    elif hue < 2/6:
        r = 1 - (hue - 1/6) * 6
        g = 1
    elif hue < 3/6:
        g = 1
        b = (hue - 2/6) * 6
    elif hue < 4/6:
        g = 1 - (hue - 3/6) * 6
        b = 1
    elif hue < 5/6:
        b = 1
        r = (hue - 4/6) * 6
    else:
        b = 1 - (hue - 5/6) * 6
        r = 1
    
    return (int(r * 255), int(g * 255), int(b * 255))

# Thread function to update hue based on time
def update_hue():
    global hue
    while True:
        elapsed_time = (pygame.time.get_ticks() - start_time) / 1000.0  # in seconds
        hue = (elapsed_time * 0.1) % 1  # Modulo 1 to cycle through colors
        time.sleep(0.01)  # Sleep for a short time to avoid using too much CPU

# Start the hue updating thread
hue_thread = threading.Thread(target=update_hue, daemon=True)
hue_thread.start()

# Game loop
running = True
while running:
    # Handle events
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Get the current color from hue
    current_color = hue_to_rgb(hue)

    # Fill the screen with black
    screen.fill((0, 0, 0))

    # Draw the band with the current color
    pygame.draw.rect(screen, current_color, (band_x, band_y, band_width, band_height))

    # Update the display
    pygame.display.flip()

    # Print the FPS in the console
    print(f"FPS: {clock.get_fps():.2f}")

    # Do not limit FPS, this loop will run as fast as possible
    clock.tick()  # This line controls time-related functions but doesn't limit FPS directly

# Quit Pygame
pygame.quit()
