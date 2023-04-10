package kyuubiforge.Graphics;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static kyuubiforge.Debug.Debug.log;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Texture2D {
    private String filePath;
    private int textureId;

    public Texture2D(String filePath, TextureSettings textureSettings) {
        this.filePath = filePath;

        //Texture id on gpu
        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, textureSettings.wrapMode.getValue());
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, textureSettings.wrapMode.getValue());

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, textureSettings.textureFilter.getValue());
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, textureSettings.textureFilter.getValue());

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        /*
            Return the number of channels (eg. RGB, RGBA)
            3 = RGB, 4 = RGBA
         */
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = stbi_load(filePath, width, height, channels, 0);

        if(image != null)
        {
            if(channels.get(0) == 3)
            {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(0), height.get(0), 0 , GL_RGB, GL_UNSIGNED_BYTE, image);
            } else if (channels.get(0) == 4)
                {
                    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
                }   else
                    {
                        log("Error: (Texture) Unknown number of channels '" + channels.get(0) + "'");
                        assert false;
                    }
        } else {
            assert false : "Error: (Texture) Could not load image '" + filePath + "'";
        }

        stbi_image_free(image);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
