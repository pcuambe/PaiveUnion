/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Paulo Amosse
 */
@Entity(name = "MATERIAL_USO")
public class MaterialUso implements Serializable {
    @Id
    @GeneratedValue
    private Integer materialUsoID;
    private String codigoMaterial;
    private String nome;
    private int quantidade;
    private int quantidadePrejuizo;
    private Date dataDeUso;
    private double precoDeAluguer;
    private String categoria;
    private String descricao;
    private int pessoasMesa;
    private int quantidadeMinima; //Nr minimo de determinado material a ser alugado.

    @ManyToOne
    private Material material;
    
    public Integer getMaterialUsoID() {
        return materialUsoID;
    }

    public void setMaterialUsoID(Integer materialUsoID) {
        this.materialUsoID = materialUsoID;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoDeAluguer() {
        return precoDeAluguer;
    }

    public void setPrecoDeAluguer(double precoDeAluguer) {
        this.precoDeAluguer = precoDeAluguer;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPessoasMesa() {
        return pessoasMesa;
    }

    public void setPessoasMesa(int pessoasMesa) {
        this.pessoasMesa = pessoasMesa;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public int getQuantidadePrejuizo() {
        return quantidadePrejuizo;
    }

    public void setQuantidadePrejuizo(int quantidadePrejuizo) {
        this.quantidadePrejuizo = quantidadePrejuizo;
    }

    public Date getDataDeUso() {
        return dataDeUso;
    }

    public void setDataDeUso(Date dataDeUso) {
        this.dataDeUso = dataDeUso;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return nome + " Quantidade: " + quantidade + " Data De Uso: " + dataDeUso;
    }
    
    
    
    
    
}
