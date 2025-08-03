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


import br.com.bliblioteca_abc.biblioteca.model.Editora;

@RestController //Indica que a classe tem endpoints REST
@RequestMapping("/editoras") //Define a rota base(tudo começa com /editoras)
public class EditoraController {

        private List<Editora> editoras = new ArrayList<>(); //Cria uma lista de editoras em memória para armazenar dados

        @GetMapping
            public List<Editora> listALL(){     //Retorna toda lista de editoras
                return editoras;
            }

         @GetMapping("/{id}") //GET /editoras/{id}
            public ResponseEntity<Editora> findById(@PathVariable Integer id) { //@PathVariable captura o {id} da URL

                return editoras.stream() //Usa um stream para procurar a editora com o ID correspondente
                    .filter(b -> b.getId() == (id))
                    .findFirst()
                    .map(ResponseEntity::ok)//Se encontrar (status 200 OK)
                    .orElse(ResponseEntity.notFound().build());//Se não encontrar status 404 Not Found
            }

           @PostMapping
            public ResponseEntity<Editora> save (@RequestBody Editora editora){//RequestBody pega os dados da requisição e converte para objeto Editora
               editoras.add(editora);//Adiciona na lista
                return ResponseEntity.status(201).body(editora);//Retorna 201 Created com o objeto salvo

            }


                  @PutMapping("/{id}")  //Percorre a lista procurando o ID
            public ResponseEntity<Editora> update(@PathVariable Integer id, @RequestBody Editora editora) {
               for (int i = 0; i < editoras.size(); i++){
                if (editoras.get(i).getId() == (id)){   //Se encontrar substitui pelo novo objeto
                    editoras.set(i, editora);
                    return ResponseEntity.ok(editora);  //Retorna 200 OK
                }
               }
               return ResponseEntity.notFound().build(); //Se não encontrar 404 Not Found
            }     
            
            
             @DeleteMapping("/{id}")  //Deleta
             public ResponseEntity<Void> delete(@PathVariable Integer id){
                boolean removed = editoras.removeIf(b -> b.getId() == (id)); //Usa removeIf para apagar a editora com o ID fornecido.
                if(removed){
                    return ResponseEntity.noContent().build();  //Se remover retorna 204 no Content (apagado com sucesso)
            }
            return ResponseEntity.notFound().build(); //Se não remover 404 Not Found
        }


}
