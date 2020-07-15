package br.com.projeto.cotacaomoeda.service;

import br.com.projeto.cotacaomoeda.entity.Moeda;
import br.com.projeto.cotacaomoeda.repository.MoedaRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class MoedaService {

    @Autowired
    private MoedaRepository repository;

    public Moeda obterContacao(String data) throws IOException {
        return repository.save(recuperaCotacao(data));
    }

    private Moeda recuperaCotacao(String dataRequicao) throws IOException {
        Moeda moeda = new Moeda();
        String ano = dataRequicao.substring(dataRequicao.length() -4);

        Document doc = Jsoup.connect("http://www.idealsoftwares.com.br/indices/dolar" + ano + ".html").get();

        Element table = doc.select("table").first();
        Elements data = doc.select("tr");

        for(Element d : data){
            String dt = d.text();
            if(dt.contains(dataRequicao)){
                String[] list = dt.split(" ");
                moeda.setRequisicao(Timestamp.valueOf(list[0]));
                moeda.setCotacaoCompra(new BigDecimal(list[1]));
                moeda.setCotacaoVenda(new BigDecimal(list[2]));
                moeda.setDtCotacao(new Date(list[0]));
            }
        }
        return moeda;
    }

    public static void main(String[] args) throws IOException {
        String dia = "14";
        String mes = "11";
        String ano = "2017";

        String dataDigitada = dia + "/" + mes + "/" + ano;

        Document doc = Jsoup.connect("http://www.idealsoftwares.com.br/indices/dolar" + ano + ".html").get();

        Element table = doc.select("table").first();
        Elements data = doc.select("tr");


        for(Element d : data){
            String dt = d.text();

            if(dt.contains(dataDigitada)){

                String[] textoSeparado = dt.split(" ");

                String dataCotacao = textoSeparado[0];
                String compra = textoSeparado[1];
                String venda = textoSeparado[2];

                System.out.println("Data: " + dataCotacao);
                System.out.println("Compra: " + compra);
                System.out.println("Venda: " + venda);
            }
        }
    }


}
