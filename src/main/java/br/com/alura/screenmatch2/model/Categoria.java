package br.com.alura.screenmatch2.model;

public enum Categoria {
    ACAO ("Action", "Ação"),
    ROMANCE ("Romance", "Romance"),
    COMEDIA ("Comedy", "Comédia"),
    DRAMA ("Drama", "Drama"),
    CRIME ("Crime", "Crime");

    //Atributos
    private String categoriaOmdb;
    private String categoriaPortugues;

    //Construtor
    Categoria(String categoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    //Getters
    public String getCategoriaOmdb() {return categoriaOmdb;}
    public String getCategoriaPortugues() {return categoriaPortugues;}


    //Métodos
    public static Categoria fromString (String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromPortugues (String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
