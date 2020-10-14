package br.com.casa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

//	@Query("SELECT DISTINCT obj FROM  Produto obj INNER JOIN obj.categorias cat "
//			+ "WHERE obj.nome LIKE %:nome%  AND cat IN :categorias")
//	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable page);
	// TANTO POR JPQL quanto por nome de método
	/**
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
	 * 
	 **/
	@Transactional(readOnly = true) // Não é necessário manter transação
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable page);

}
