package com.semantica.yada.indexfileservice;

import com.semantica.yada.events.FileUploadedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IndexFileEventHandler {

    @EventHandler
    public void on(FileUploadedEvent evt) throws Exception {

        String uuid = evt.getFileId().toString();
        IndexFile.indexFile(uuid);

    }
}
