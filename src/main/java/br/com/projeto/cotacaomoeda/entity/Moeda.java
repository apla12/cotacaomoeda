package br.com.projeto.cotacaomoeda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "moeda")
public class Moeda {

    @Id
    private String id;
    private Timestamp requisicao;
    private Date cotacaoMoeda;//Date
    private BigDecimal cotacaoCompra; //BigDecimal
    private BigDecimal cotacaoVenda;//BigDecimal
    private Date dtCotacao; //Date

}
