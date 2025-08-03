package br.com.bliblioteca_abc.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Livro {

    private int id;
    private String issn;
    private String titulo;
    private String sinopse;
    private int ano;
    private int numeroPaginas;

}
