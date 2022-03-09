package getta.gif2png;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean noHaConseguidoAcabar;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buenas tardes señora, ha iniciado el conversor de gif a png.");
        System.out.println("Espero que este teniendo un agradable dia.");
        System.out.println("Yo si, gracias por preguntar.");
        while (true) {
            noHaConseguidoAcabar = false;
            System.out.println("Ahora es el momento en el que usted me dira la ruta de la que quiere que yo coja el gif y lo convierta en el png.");
            System.out.println("Porfavor siga este ejemplo.");
            System.out.println(">C:\\MeGustanLosPitos\\MiGif.gif");
            String path = scanner.nextLine();
            if (path.equals("")) {
                System.out.println("Por favor, introduzca una ruta que sea una ruta, trollo.");
                noHaConseguidoAcabar = true;
            }
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("No se ha podido encontrar el archivo que usted estimado señor, este dirigiendose.");
                System.out.println("Por favor vuelvete a leer las instrucciones de como usarlo. :)");
                noHaConseguidoAcabar = true;
            } else if (!file.canRead()) {
                System.out.println("Por favor, abreme con administrador. .)");
                noHaConseguidoAcabar = true;
            }
            if (!noHaConseguidoAcabar) {
                System.out.println("Se ha encontrado el gif correctamente");
                System.out.println("Introduce donde quieres que se guarde guapa.");
                System.out.println("Si no existe se creara la ruta, no te pases de carpetas perro.");
                String outFile = scanner.nextLine();
                try {
                    File file1 = new File(outFile);
                    file1.mkdirs();
                } catch (Exception e) {
                    System.out.println("No se que has hecho pero tu ruta da pena y no le da la gana crearla crack.");
                    continue;
                }
                System.out.println("Ya se que estas harto pero ahora di el nombre con el que quieres que lo exporte");
                String name = scanner.nextLine();

                ImageReader reader = ImageIO.getImageReadersBySuffix("GIF").next();
                try {
                    ImageInputStream in = ImageIO.createImageInputStream(file);
                    reader.setInput(in);
                    BufferedImage[] images = new BufferedImage[reader.getNumImages(true)];
                    for (int i = 0, count = reader.getNumImages(true); i < count; i++) {
                        BufferedImage image = reader.read(i);
                        images[i] = image;
                    }
                    //Get all widths and heigth of the final image
                    BufferedImage size = images[0];
                    for (int i = 1; i < images.length; i++) {
                        BufferedImage image = images[i];
                        int newImgWidth = Math.max(size.getWidth(), image.getWidth());
                        int newImgHeight = size.getHeight() + image.getHeight();
                        size = new BufferedImage(newImgWidth, newImgHeight, BufferedImage.TYPE_INT_RGB);
                    }
                    BufferedImage finalImage = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    int heightCurrent = 0;
                    Graphics2D graphics2D = finalImage.createGraphics();
                    for (BufferedImage image : images) {
                        graphics2D.drawImage(image, 0, heightCurrent, null);
                        heightCurrent += image.getHeight();
                    }
                    ImageIO.write(finalImage, "png", new File(outFile + "\\" + name + ".png"));
                    System.out.println("He acabado");
                } catch (Exception e) {
                    System.out.println("Eres un inutil y has conseguido que pete, grande.");
                }
            }
            System.out.println("Si quieres salir escribe : me gusta el pene, si quieres exportear otra imagen escribe lo que quieras :D");
            if (scanner.nextLine().equals("me gusta el pene")) {
                System.exit(0);
            }
        }
    }
}
