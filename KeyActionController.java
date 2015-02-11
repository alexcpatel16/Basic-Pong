import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class KeyActionController extends AbstractAction {
   private PicturePanel picture;
   private String command;

   public KeyActionController(PicturePanel p, String c) {
      picture = p;
      command = c;
   }

   public void actionPerformed(ActionEvent e) {
      if(command.equals("UP")) {
         picture.moveUp();
      } else if(command.equals("DOWN")) {
         picture.moveDown();
      } else if(command.equals("W")) {
         picture.moveW();
      } else if(command.equals("S")) {
         picture.moveS();
      }  
   }
}
