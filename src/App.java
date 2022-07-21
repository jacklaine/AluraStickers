import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

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
        
        String urlImagem = filme.get("image");
        String titulo = filme.get("title");

        InputStream inputStream = new URL(urlImagem).openStream();
        String nomeArquivo = titulo + ".png";

        var geradora = new GeradoraDeFigurinhas();
        geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }

    }
}
