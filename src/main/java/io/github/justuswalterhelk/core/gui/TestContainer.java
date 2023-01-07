package io.github.justuswalterhelk.core.gui;

import static org.lwjgl.opengl.GL20.*;

public class TestContainer extends Container {

    private String vertexShaderSrc = "#version 330 core\n" +
            "layout (location=0) in vec3 aPos;\n" +
            "layout (location=1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "}";
    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    color = fColor;\n" +
            "}";

    // Ids for vertex and fragment shaders, shaderprogramm is the combined value
    private int vertexID, fragmentID, shaderProgramm;

    public TestContainer()
    {
        this.containerName = "TestContainer";
        this.containerDescription = "Container for testing basic rendering and texture bashing";
    }

    @Override
    public void init()
    {
        System.out.println("Initializing " + containerName);
        //Compile shaders
        //Vertex
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        //Compile it
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);

        //Error handling
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if(success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("Error in 'defaultShader.glsl' \n\tVertex shader compilation failed");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        //Compile it
        glShaderSource(fragmentID, fragmentShaderSrc);
        glCompileShader(fragmentID);

        //Error handling
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if(success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("Error in 'defaultShader.glsl' \n\tFragment shader compilation failed");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

        //Link shaders
        shaderProgramm = glCreateProgram();
        glAttachShader(shaderProgramm, vertexID);
        glAttachShader(shaderProgramm, fragmentID);
        glLinkProgram(shaderProgramm);

        //Errors
        success = glGetProgrami(shaderProgramm, GL_LINK_STATUS);
        if(success == 0)
        {
            int length = glGetProgrami(shaderProgramm, GL_INFO_LOG_LENGTH);
            System.out.println("Error in 'defaultShader.glsl' \n\tLinking shaders failed");
            System.out.println(glGetProgramInfoLog(shaderProgramm, length));
            assert false : "";
        }
    }

    @Override
    public void update(float deltaTime)
    {

    }
}
