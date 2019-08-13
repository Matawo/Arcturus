package Utils;

import Model.Creature;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class SaverXML {
    public static void serializeCreature(Creature creature) {
        XMLEncoder encoder = null;
        String filename = "Saves/"+ creature.getName() + creature.getId();
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
            encoder.writeObject(creature);
            encoder.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (encoder != null) {
                encoder.close();
            }
        }
    }
}
