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
public class Editora {

    private int id;
    private String  nome;
    private String endereco;
    private String telefone;

}
