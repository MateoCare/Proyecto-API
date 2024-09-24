package com.uade.api.ecommerce.ecommerce.jobs;

import com.uade.api.ecommerce.ecommerce.services.HistorialProductosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class EliminarHistorialAntiguoJob {

    @Autowired
    private Environment env;

    @Autowired
    private HistorialProductosService historialProductosService;

    @Scheduled(fixedRate = 120000)
    public void eliminarRegistrosAntiguosSiEsNecesario() {
        log.info("EliminarHistorialAntiguoJob: se limpian los registros antiguos del historial de vistos");

        int limit = env.getProperty("consulta.vistos.recientemente.limit", Integer.class, 10);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -limit);
        Date limitDate = calendar.getTime();

        int count =  historialProductosService.eliminarHistorialAntiguo(limitDate);
        log.info("EliminarHistorialAntiguoJob: se eliminaron {} registros", count);
    }
}
