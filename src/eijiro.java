
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hdm
 */
public class eijiro {
public static ClassProp propC = new ClassProp();
private static EijiroJFrame eijiroFrm;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //プロパティファイルの読み込み
        Properties config = new Properties();
        try {
            //config.load(new FileInputStream("card.properties"));
            config.load(new InputStreamReader(new FileInputStream("eijiro.properties"), "UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        propC.EijiroPath = config.getProperty("EijiroPath", "./eijiro");
        propC.WaeiPath = config.getProperty("WaeiPath", "./waei");
        
        eijiroFrm = new EijiroJFrame();
        
        int x = Integer.parseInt(config.getProperty("x", "100"));
        int y = Integer.parseInt(config.getProperty("y", "100"));
        int width = Integer.parseInt(config.getProperty("width", "400"));
        int height = Integer.parseInt(config.getProperty("height", "300"));
        eijiroFrm.setBounds(x, y, width, height);
        
        String str = "";
        Toolkit kit = Toolkit.getDefaultToolkit();
        Clipboard clip = kit.getSystemSelection();
        try {
                str = (String) clip.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
                //return null;
        } catch (IOException e) {
                //return null;
        } catch (Exception e) {
                clip = kit.getSystemClipboard();
                try {
                        str = (String) clip.getData(DataFlavor.stringFlavor);
                } catch (Exception e2) {
                        //
                }
        }
        eijiroFrm.setText(str);
        
        eijiroFrm.setVisible(true);
        eijiroFrm.readFile(false);
    }
    
    public static void writeProp() {
        //プロパティファイルの書き込み
        Properties config = new Properties();
        Rectangle rect = eijiroFrm.getBounds();
        config.setProperty("x", String.valueOf(rect.x));
        config.setProperty("y", String.valueOf(rect.y));
        config.setProperty("width", String.valueOf(rect.width));
        config.setProperty("height", String.valueOf(rect.height));
        
        config.setProperty("EijiroPath", propC.EijiroPath);
        config.setProperty("WaeiPath", propC.WaeiPath);
        
        try {
            config.store(new OutputStreamWriter(new FileOutputStream("eijiro.properties"),"UTF-8"), "by HDM");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
