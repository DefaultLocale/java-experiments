package kz.defaultlocale.vaadin8.samples;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Image;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * Draws an image in runtime
 *
 * @author MAK
 */
public class ImageResourceSample extends Sample {

    public ImageResourceSample() {
        super("Custom image resource");
    }

    @Override
    protected void init() {
        StreamSource imagesource = new MyImageSource();
        StreamResource resource
                = new StreamResource(imagesource, "myimage.png");
        addComponent(new Image("Image title", resource));
    }

    private static class MyImageSource implements StreamSource {

        ByteArrayOutputStream imagebuffer = null;
        int reloads = 0;

        public MyImageSource() {
        }

        @Override
        public InputStream getStream() {
            // Create an image
            BufferedImage image = new BufferedImage(400, 400,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D drawable = image.createGraphics();
// Draw something static
            drawable.setStroke(new BasicStroke(5));
            drawable.setColor(Color.WHITE);
            drawable.fillRect(0, 0, 400, 400);
            drawable.setColor(Color.BLACK);
            drawable.drawOval(50, 50, 300, 300);
// Draw something dynamic
            drawable.setFont(new Font("Montserrat",
                    Font.PLAIN, 48));
            drawable.drawString("Reloads=" + reloads, 75, 216);
            reloads++;
            drawable.setColor(new Color(0, 165, 235));
            int x = (int) (200 - 10 + 150 * Math.sin(reloads * 0.3));
            int y = (int) (200 - 10 + 150 * Math.cos(reloads * 0.3));
            drawable.fillOval(x, y, 20, 20);
            try {
// Write the image to a buffer
                imagebuffer = new ByteArrayOutputStream();
                ImageIO.write(image, "png", imagebuffer);
// Return a stream from the buffer
                return new ByteArrayInputStream(
                        imagebuffer.toByteArray());
            } catch (IOException e) {
                return null;
            }
        }
    }

}
