import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // Conexão HTTP
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // extrair os dados: titulo, poster, classificacao
        var parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);
        System.out.println(listaFilmes.size());
        System.out.println(listaFilmes.get(0));

        // exibir e manipular dados
        for (Map<String, String> filme : listaFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("year"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
            System.out.println();
        }

    }
}
