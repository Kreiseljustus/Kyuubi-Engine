package kyuubiforge.Renderer;

import static kyuubiforge.Debug.Debug.log;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class Shader
{
    private static HashMap<Integer, Shader> shaders = new HashMap<>();

    private int shaderProgramID;

    private String vertexSource;
    private String fragmentSource;
    private String filePath;

    public Shader(String filePath) {
        this.filePath = filePath;
        getSource();
    }

    private void getSource()
    {
        try
        {
            String source = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] splitString = source.split("(#type)( )+([a-zA-Z]+)");

            //Next word
            int index = source.indexOf("#type") + 6;
            int endOfLine = source.indexOf("\r\n", index);
            String firstPattern = source.substring(index, endOfLine).trim();

            index = source.indexOf("#type", endOfLine) + 6;
            endOfLine = source.indexOf("\r\n", index);
            String secondPattern = source.substring(index,endOfLine).trim();

            if(firstPattern.equals("vertex"))
            {
                vertexSource = splitString[1];
            } else if(firstPattern.equals("fragment"))
            {
                fragmentSource = splitString[1];
            }
            else
            {
                throw new IOException("Unexpected token " + firstPattern + " in " + filePath);
            }

            if(secondPattern.equals("vertex"))
            {
                vertexSource = splitString[2];
            } else if(secondPattern.equals("fragment"))
            {
                fragmentSource = splitString[2];
            }
            else
            {
                throw new IOException("Unexpected token " + firstPattern + " in " + filePath);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            assert false: "Error: Could not open file for shader: " + filePath;
        }
    }

    public void compile()
    {
        int vertexID, fragmentID;

        vertexID = glCreateShader(GL_VERTEX_SHADER);
        //Compile it
        glShaderSource(vertexID, vertexSource);
        glCompileShader(vertexID);

        //Error handling
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if(success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            log("Error in 'defaultShader.glsl' \n\tVertex shader compilation failed");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        //Compile it
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);

        //Error handling
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if(success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            log("Error in 'defaultShader.glsl' \n\tFragment shader compilation failed");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

        //Link shaders
        shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID, vertexID);
        glAttachShader(shaderProgramID, fragmentID);
        glLinkProgram(shaderProgramID);

        //Errors
        success = glGetProgrami(shaderProgramID, GL_LINK_STATUS);
        if(success == 0)
        {
            int length = glGetProgrami(shaderProgramID, GL_INFO_LOG_LENGTH);
            log("Error in '"+ filePath + " ' \n\tLinking shaders failed");
            System.out.println(glGetProgramInfoLog(shaderProgramID, length));
            assert false : "";
        }

        shaders.put(shaderProgramID, this);
    }

    public void uploadMat4f(String varName, Matrix4f mat4)
    {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat4.get(matBuffer);
        glUniformMatrix4fv(varLocation, false, matBuffer);
    }

    public void use()
    {
        glUseProgram(shaderProgramID);
    }

    public void reload() { detach(); getSource(); compile();}

    public void detach()
    {
        glUseProgram(0);
    }

    public static HashMap<Integer, Shader> getShaders()
    {
        return shaders;
    }
}
