package br.edu.icomp.plaintext;

public class Password {
    private int id; // identificador (inteiro) da senha
    private String name; // um nome para a senha (e.g., nome do site)
    private String login; //login: login de acesso da senha
    private String password; //password: a senha a ser cadastrada
    private String notes; //notes: observações gerais

    Password(int id, String name, String login, String password, String notes) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.notes = notes;
    }

    // Getters and Setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public String getNotes(){
        return  notes;
    }
}
