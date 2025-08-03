package br.com.bliblioteca_abc.biblioteca.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.bliblioteca_abc.biblioteca.model.Livro;

@RestController //Indica que a classe tem endpoints REST
@RequestMapping("/livros") //Define a rota base(tudo começa com /livros)
public class LivroController {


        private List<Livro> livros = new ArrayList<>();//Cria uma lista de livros em memória para armazenar dados

        @GetMapping
        public List<Livro> listAll(){   //Retorna toda lista de livros
            return livros;
        }

        @GetMapping("/{id}") //GET /livros/{id}
            public ResponseEntity<Livro> findById(@PathVariable Integer id) { //@PathVariable captura o {id} da URL

                return livros.stream() //Usa um stream para procurar a livro com o ID correspondente
                    .filter(b -> b.getId() == (id))
                    .findFirst()
                    .map(ResponseEntity::ok)//Se encontrar (status 200 OK)
                    .orElse(ResponseEntity.notFound().build());//Se não encontrar status 404 Not Found
            }
        

              @PostMapping
            public ResponseEntity<Livro> save (@RequestBody Livro  livro){//RequestBody pega os dados da requisição e converte para objeto Livro
                livros.add(livro);//Adiciona na lista
                return ResponseEntity.status(201).body(livro);//Retorna 201 Created com o objeto salvo

            }

               @PutMapping("/{id}")  //Percorre a lista procurando o ID
            public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro livro) {
               for (int i = 0; i < livros.size(); i++){
                if (livros.get(i).getId() == (id)){   //Se encontrar substitui pelo novo objeto
                    livros.set(i, livro);
                    return ResponseEntity.ok(livro);  //Retorna 200 OK
                }
               }
               return ResponseEntity.notFound().build(); //Se não encontrar 404 Not Found
            }


         @DeleteMapping("/{id}")  //Deleta
             public ResponseEntity<Void> delete(@PathVariable Integer id){
                boolean removed = livros.removeIf(b -> b.getId() == (id)); //Usa removeIf para apagar a livro com o ID fornecido.
                if(removed){
                    return ResponseEntity.noContent().build();  //Se remover retorna 204 no Content (apagado com sucesso)
            }
            return ResponseEntity.notFound().build(); //Se não remover 404 Not Found
        }

}
