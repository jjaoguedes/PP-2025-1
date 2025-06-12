public class Equipamento {
    private String tombamento;
    private String tipo;
    private String descricao;
    private String dataAquisicao;
    private String responsavel;
    private String localizacao;

    public Equipamento(String tombamento, String tipo, String descricao, String dataAquisicao, String responsavel, String localizacao) {
        this.tombamento = tombamento;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataAquisicao = dataAquisicao;
        this.responsavel = responsavel;
        this.localizacao = localizacao;
    }

    public String getTombamento() {
        return tombamento;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getLocalizacao() {
        return localizacao;
    }
}
