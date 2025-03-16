import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.Callbacks.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

public class LWJGLExample {

    private long window;
    private int width = 800;
    private int height = 600;

    private float rotationAngle = 0;

    public static void main(String[] args) {
        new LWJGLExample().run();
    }

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        window = glfwCreateWindow(width, height, "LWJGL 3 Example", MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    window,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        GL.createCapabilities();
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Set up the camera
            GL11.glLoadIdentity();
            GL11.glTranslatef(0.0f, 0.0f, -5.0f);

            // Rotate the cube
            GL11.glRotatef(rotationAngle, 1.0f, 1.0f, 0.0f);

            // Draw the cube
            drawCube();

            rotationAngle += 0.5f;

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void drawCube() {
        GL11.glBegin(GL11.GL_QUADS);

        // Front face
        GL11.glColor3f(1.0f, 0.0f, 0.0f); // Red
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);

        // Back face
        GL11.glColor3f(0.0f, 1.0f, 0.0f); // Green
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f);

        // Left face
        GL11.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);

        // Right face
        GL11.glColor3f(1.0f, 1.0f, 0.0f); // Yellow
        GL11.glVertex3f(1.0f, -1.0f, -1.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f);

        // Top face
        GL11.glColor3f(1.0f, 0.0f, 1.0f); // Magenta
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f);

        // Bottom face
        GL11.glColor3f(0.0f, 1.0f, 1.0f); // Cyan
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f);

        GL11.glEnd();
    }

    private void cleanup() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
