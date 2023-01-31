package io.github.justuswalterhelk.KyuubiForge.gui;

import io.github.justuswalterhelk.KyuubiForge.input.Key;
import io.github.justuswalterhelk.KyuubiForge.input.KeyListener;
import io.github.justuswalterhelk.KyuubiForge.rendering.Shader;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class TestContainer extends Container {

    private Shader shader = null;

    //Normalized Device Coordinates!
    private float[] vertexArray =
            {
                //position                  //color
                0.5f, -0.5f, 0.0f,           0.0f,1.0f, 1.0f, 1.0f,
                -0.5f, 0.5f, 0.0f,            0.0f, 1.0f,0.0f, 1.0f,
                0.5f, 0.5f, 0.0f,             0.0f, 0.0f, 1.0f, 1.0f,
                -0.5f, -0.5f, 0.0f,          1.0f, 1.0f, 0.0f, 1.0f,
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
        System.out.println("Initializing " + containerName);

        shader = new Shader("assets/shaders/default.glsl");

        shader.compile();

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
        int floatSizeBytes = 4;
        int vertexSizeBytes = (positionSize + colorSize) * floatSizeBytes;
        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * floatSizeBytes);
        glEnableVertexAttribArray(1);
    }

    @Override
    public void update(float deltaTime)
    {
        if(KeyListener.isKeyPressed(Key.R.getValue()))
        {
            //Reload Shader
            shader.reload();
        }

        shader.use();
        //Bind vao
        glBindVertexArray(vaoID);

        //Enable vertex attr
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        //Unbind clearup
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
        shader.detach();
    }
}
