package Utils;

import Model.Creature;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SaverXML {
    public static void save(Creature creature) {
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

    public static Creature load(String filename) {
        XMLDecoder decoder = null;
        Creature c = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
            c = (Creature) decoder.readObject();
            System.out.println(c);
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            if (decoder != null) {
                decoder.close();
            }
        }
        return c;
    }
}
