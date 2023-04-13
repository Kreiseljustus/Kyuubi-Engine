package kyuubiforge.Core.Window.Container;

import kyuubiforge.Core.Window.Container.Container;
import static kyuubiforge.Debug.Debug.log;

import kyuubiforge.Graphics.Texture2D;
import kyuubiforge.Graphics.TextureSettings;
import kyuubiforge.Input.Key;
import kyuubiforge.Input.KeyListener;
import kyuubiforge.Renderer.EditorCamera;
import kyuubiforge.Renderer.Shader;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;


/*
    This class needs to get reworked at some point.
    This class will not be included in the production release of the engine,
    it´s currently used for testing purposes only!
 */
public class TestContainer extends Container {

    private Shader shader = null;
    private Texture2D texture2D;

    private EditorCamera camera;

    //Normalized Device Coordinates!
    private float[] vertexArray =
            {
                    // position               // color                  // UV Coordinates
                    100f,   0f, 0.0f,       1.0f, 0.0f, 0.0f, 1.0f,     1, 1, // Bottom right 0
                    0f, 100f, 0.0f,       0.0f, 1.0f, 0.0f, 1.0f,     0, 0, // Top left     1
                    100f, 100f, 0.0f ,      1.0f, 0.0f, 1.0f, 1.0f,     1, 0, // Top right    2
                    0f,   0f, 0.0f,       1.0f, 1.0f, 0.0f, 1.0f,     0, 1  // Bottom left  3
            };

    //COUNTER CLOCKWISE!
    private int[] elementArray =
    {
            2,1,0,
            0,1,3
    };

    public TestContainer()
    {
        this.containerName = "TestContainer";
        this.containerDescription = "Container for testing basic rendering and texture bashing";
    }

    private  int vaoID, vboID, eboID;

    @Override
    public void init()
    {
        this.camera = new EditorCamera(new Vector3f(-600.0f,-300.0f, 0.1f));
        log("Initializing " + containerName);

        shader = new Shader("assets/shaders/default.glsl");

        shader.compile();

        texture2D = new Texture2D("assets/images/texture.jpg", new TextureSettings());

        //Generate VAO VBO EBO buffers
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        //Create float buffer with vertices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();
        //VBO
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        //Indices
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();
        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);
        //vertex attribute pointers
        int positionSize = 3;
        int colorSize = 4;
        int uvSize = 2;
        int vertexSizeBytes = (positionSize + colorSize + uvSize) * Float.BYTES;
        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionSize + colorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);
    }

     float cameraSpeed = 2f;

    @Override
    public void update(float deltaTime) {

        //System.out.println("[KyuubiForge] Container updated with " + deltaTime);
        if(KeyListener.isKeyPressed(Key.R.getValue()))
        {
            //Reload Shader
            shader.reload();
        }

        if(KeyListener.isKeyPressed(Key.A.getValue()))
        {
            camera.position.x -= cameraSpeed * deltaTime * 20f;
        }
        if(KeyListener.isKeyPressed(Key.D.getValue()))
        {
            camera.position.x += cameraSpeed * deltaTime * 20f;
        }
        if(KeyListener.isKeyPressed(Key.W.getValue()))
        {
            camera.position.y -= cameraSpeed * deltaTime * 20f;
        }
        if(KeyListener.isKeyPressed(Key.S.getValue()))
        {
            camera.position.y += cameraSpeed * deltaTime * 20f;
        }

        shader.use();

        shader.uploadTexture("TEX_SAMPLER", 0);
        glActiveTexture(GL_TEXTURE0);
        texture2D.bind();

        shader.uploadMat4f("uProjection", camera.getProjectionMatrix());
        shader.uploadMat4f("uView", camera.getViewMatrix());
        //Bind vao
        glBindVertexArray(vaoID);

        //Enable vertex attr
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        //Unbind clear up
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);
        shader.detach();
    }
}
