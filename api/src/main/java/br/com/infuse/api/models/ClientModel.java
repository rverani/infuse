package br.com.infuse.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CLIENTS_TB")
@Getter
@Setter
public class ClientModel extends RepresentationModel<ClientModel> implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    private Date dtCadastro;
    private String name;
    private String addressClient;
}
