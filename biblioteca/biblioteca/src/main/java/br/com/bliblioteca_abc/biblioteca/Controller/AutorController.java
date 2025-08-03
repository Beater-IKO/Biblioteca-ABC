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

import br.com.bliblioteca_abc.biblioteca.model.Autor;



@RestController //Indica que a classe tem endpoints REST
@RequestMapping("/autores") //Define a rota base(tudo começa com /autores)
public class AutorController {

       private List<Autor> autores = new ArrayList<>(); //Cria uma lista de autores em memória para armazenar dados


    @GetMapping
            public List<Autor> listALL(){     //Retorna toda lista de autores
                return autores;
            }

    @GetMapping("/{id}") //GET /autores/{id}
            public ResponseEntity<Autor> findById(@PathVariable Integer id) { //@PathVariable captura o {id} da URL

                return autores.stream() //Usa um stream para procurar o autor com o ID correspondente
                    .filter(b -> b.getId() == (id))
                    .findFirst()
                    .map(ResponseEntity::ok)//Se encontrar (status 200 OK)
                    .orElse(ResponseEntity.notFound().build());//Se não encontrar status 404 Not Found
            }
        
         @PostMapping
            public ResponseEntity<Autor> save (@RequestBody Autor autor){//RequestBody pega os dados da requisição e converte para objeto Autor
                autores.add(autor);//Adiciona na lista
                return ResponseEntity.status(201).body(autor);//Retorna 201 Created com o objeto salvo

            }  


    @PutMapping("/{id}")  //Percorre a lista procurando o ID
            public ResponseEntity<Autor> update(@PathVariable Integer id, @RequestBody Autor autor) {
               for (int i = 0; i < autores.size(); i++){
                if (autores.get(i).getId() == (id)){   //Se encontrar substitui pelo novo objeto
                    autores.set(i, autor);
                    return ResponseEntity.ok(autor);  //Retorna 200 OK
                }
               }
               return ResponseEntity.notFound().build(); //Se não encontrar 404 Not Found
            }

         @DeleteMapping("/{id}")  //Deleta
            public ResponseEntity<Void> delete(@PathVariable Integer id){
                boolean removed = autores.removeIf(b -> b.getId() == (id)); //Usa removeIf para apagaro autor com o ID fornecido.
                if(removed){
                    return ResponseEntity.noContent().build();  //Se remover retorna 204 no Content (apagado com sucesso)
            }
            return ResponseEntity.notFound().build(); //Se não remover 404 Not Found
        }


}
