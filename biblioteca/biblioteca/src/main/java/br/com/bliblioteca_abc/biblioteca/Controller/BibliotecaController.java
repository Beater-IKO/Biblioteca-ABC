package br.com.bliblioteca_abc.biblioteca.Controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bliblioteca_abc.biblioteca.model.Biblioteca;
import org.springframework.web.bind.annotation.PutMapping;



@RestController //Indica que a classe tem endpoints REST
@RequestMapping("/bibliotecas") //Define a rota base(tudo começa com /bibliotecas)
public class BibliotecaController {


            private List<Biblioteca> bibliotecas = new ArrayList<>(); //Cria uma lista de bibliotecas em memória para armazenar dados

            @GetMapping
            public List<Biblioteca> listALL(){     //Retorna toda lista de bibliotecas
                return bibliotecas;
            }

            @GetMapping("/{id}") //GET /bibliotecas/{id}
            public ResponseEntity<Biblioteca> findById(@PathVariable Integer id) { //@PathVariable captura o {id} da URL

                return bibliotecas.stream() //Usa um stream para procurar a biblioteca com o ID correspondente
                    .filter(b -> b.getId() == (id))
                    .findFirst()
                    .map(ResponseEntity::ok)//Se encontrar (status 200 OK)
                    .orElse(ResponseEntity.notFound().build());//Se não encontrar status 404 Not Found
            }

            @PostMapping
            public ResponseEntity<Biblioteca> save (@RequestBody Biblioteca biblioteca){//RequestBody pega os dados da requisição e converte para objeto Biblioteca
                bibliotecas.add(biblioteca);//Adiciona na lista
                return ResponseEntity.status(201).body(biblioteca);//Retorna 201 Created com o objeto salvo

            }

            @PutMapping("/{id}")  //Percorre a lista procurando o ID
            public ResponseEntity<Biblioteca> update(@PathVariable Integer id, @RequestBody Biblioteca biblioteca) {
               for (int i = 0; i < bibliotecas.size(); i++){
                if (bibliotecas.get(i).getId() == (id)){   //Se encontrar substitui pelo novo objeto
                    bibliotecas.set(i, biblioteca);
                    return ResponseEntity.ok(biblioteca);  //Retorna 200 OK
                }
               }
               return ResponseEntity.notFound().build(); //Se não encontrar 404 Not Found
            }

            @DeleteMapping("/{id}")  //Deleta
            public ResponseEntity<Void> delete(@PathVariable Integer id){
                boolean removed = bibliotecas.removeIf(b -> b.getId() == (id)); //Usa removeIf para apagar a biblioteca com o ID fornecido.
                if(removed){
                    return ResponseEntity.noContent().build();  //Se remover retorna 204 no Content (apagado com sucesso)
            }
            return ResponseEntity.notFound().build(); //Se não remover 404 Not Found
        }

}
