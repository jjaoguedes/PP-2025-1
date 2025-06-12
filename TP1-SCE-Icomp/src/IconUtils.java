

import javax.swing.*;
import java.net.URL;

public class IconUtils {
    public static ImageIcon loadIcon(String name) {
        URL url = IconUtils.class.getResource("/imgs/" + name);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            System.err.println("Ícone não encontrado: " + name);
            return new ImageIcon(); // Ícone vazio
        }
    }
}
