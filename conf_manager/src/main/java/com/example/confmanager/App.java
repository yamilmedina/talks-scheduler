package com.example.confmanager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static com.example.confmanager.misc.Constants.*;
import com.example.confmanager.model.Talk;
import com.example.confmanager.scheduler.TalkScheduler;

/**
 * Punto de entrada a la aplicacion de gestion de temas.
 *
 * @author yamil
 */
public class App {

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get(WORKING_DIR + File.separator + args[0]))) {
            //filtro entradas validas.
            //separo titulo de duracion.
            //construyo charla (sin tiempo de inicio) segun los argumentos recibidos.
            List<Talk> talksPool = stream.filter(l -> l.matches(VALID_INPUT))
                    .map(l -> {
                        String title = l.split(TITLE)[0];
                        String time = l.split(TIME)[1];
                        int minutes = "lightning".equalsIgnoreCase(time.trim()) ? 5 : Integer.parseInt(time.replaceAll("min", ""));
                        return new Talk(title, minutes);
                    }).collect(Collectors.toList());
            //preparo el manager de temas y finalmente lo imprimo por pantalla.
            TalkScheduler talkScheduler = new TalkScheduler(talksPool);
            talkScheduler.schedule();
            talkScheduler.print();
        } catch (Exception e) {
            System.err.println("Error code: -1;Error message:" + e.getMessage());
        }
    }
}
