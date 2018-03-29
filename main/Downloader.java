package main;

import java.util.*;
import jdk.incubator.http.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class Downloader {
  public static final String[] DOIS = new String[]{ "10.1007/3-540-29623-9", "10.1007/978-3-642-38954-2", "10.1007/978-3-540-74339-2" };
  private final ExecutorService pool;

  private Downloader(){
    int poolSize = 3;
    pool = Executors.newFixedThreadPool(poolSize);
  }

  public static void main(String[] args) {
    Downloader downloader = new Downloader();
    final Counter c = new Counter();
    //downloader.concurrent(c);
    downloader.serial(c);
    c.print();
    downloader.shutdown();
  }

  public void serial(Counter c) {
    for (String doi: DOIS) {
      new Handler(doi, c).run();
    }
  }

  public void concurrent(Counter c) { // run the service
    List<Future<?>> results = new ArrayList<>();
    for (String doi: DOIS) {
      results.add(pool.submit(new Handler(doi, c)));
    }

    results.stream().forEach(x -> {
      try{
        x.get();
      } catch (Throwable e) {
        //
      }
    });
  }

  private void shutdown() {
    pool.shutdown();
  }

  static class Counter {
    private int i = 0;

    public void inc() {
      i++;
    }

    public void print() {
      System.out.println(i);
    }
  }

  class Handler implements Runnable {
    private String doi;
    private Counter c;

    Handler(String doi, Counter c) {
      this.doi = doi;
      this.c = c;
    }

    public void run() {
      try {
        HttpClient client = HttpClient.newHttpClient();

        // GET
        HttpResponse<String> response = client.send(
            HttpRequest
            .newBuilder(new URI("https://search.crossref.org/?q=" + doi))
            .GET()
            .build(),
            HttpResponse.BodyHandler.asString()
            );
        int statusCode = response.statusCode();
        if (statusCode == 200) {
          c.inc();
        }
      } catch (IOException | URISyntaxException | InterruptedException e) {
        System.out.println("err!");
      }
    }
  }
}
