package com.austral.triviagoservice.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Setter
@Entity
public class InvitationCode {
    /**
     * Esta clase simula un codigo de invitación para evitar uuids repetidos
     * Se guardaran en un repositorio para tener un registro de los códigos usados
     */

    @Id
    private String uuid;

    @Column
    private Boolean inUse;

    @Column
    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate creationDate;

    public InvitationCode(){}

    public static String generateNewUUID(String repeated){
        //Genera un nuevo uuid que no sea repetido
        String newUUID = repeated;
        while(newUUID.equals(repeated)){
            newUUID = UUID.randomUUID().toString();
        }
        return newUUID;
    }

    public static InvitationCode createInvitationCode(){
        //Crea una instancia de codigo de invitación
        InvitationCode code = new InvitationCode();
        String uuid = UUID.randomUUID().toString();
        code.setUuid(uuid);
        code.setInUse(true);
        code.setCreationDate(LocalDate.now(ZoneId.of("AGT")));
        return code;
    }
}
