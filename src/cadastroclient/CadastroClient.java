/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastroclient;

/**
 *
 * @author abrah
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.StreamCorruptedException;
import java.io.InvalidClassException;

public class CadastroClient {
    public static void main(String[] args) {
        try {
            // Instanciar um Socket apontando para localhost, na porta 4321
            Socket socket = new Socket("localhost", 4321);

            // Encapsular os canais de entrada e saída do Socket em objetos dos tipos
            // ObjectOutputStream (saída) e ObjectInputStream (entrada)
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Escrever o login e a senha na saída, utilizando os dados de algum dos registros
            // da tabela de usuários (op1/op1)
            String login = "op1";
            String senha = "op1";
            output.writeObject(login);
            output.writeObject(senha);
            output.writeObject("L");
            // Receber a coleção de entidades no canal de entrada
            System.out.println("Usuario conectado com sucesso");
            try {
                String[] produtoNames = (String[]) input.readObject();
                for (String nome : produtoNames) {
                    System.out.println(nome);
                }
            } catch (StreamCorruptedException e) {
                System.err.println("Stream corrupted: " + e.getMessage());
            } catch (InvalidClassException e) {
                System.err.println("Invalid class: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("IO error: " + e.getMessage());
            }
            socket.close();
        } catch (IOException e) {
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao ler objeto: " + e.getMessage());
        }
    }
}

