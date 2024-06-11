package br.com.infuse.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "REQUEST_TB")
@Getter
@Setter
public class RequestModel extends RepresentationModel<RequestModel> implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idRequest;
    private int idControl;
    private Date dtCadastro;
    private String name;
    private BigDecimal value;
    private int quantity;
    private String idClient;
}
