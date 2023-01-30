package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("Doten");
        publisher.setCity("Chicago");
        publisher.setState("Missouri");

        publisherRepository.save(publisher);

        System.out.println("Publisher count: " + publisherRepository.count());
        Author jayvee = new Author("jayvee", "ascano");
        Book fateDoujinshi = new Book("Fate Stay Night Doujinshi", "234234");
        jayvee.getBooks().add(fateDoujinshi);
        fateDoujinshi.getAuthors().add(jayvee);
        fateDoujinshi.setPublisher(publisher);
        publisher.getBooks().add(fateDoujinshi);

        authorRepository.save(jayvee);
        bookRepository.save(fateDoujinshi);
        publisherRepository.save(publisher);

        System.out.println("Started in SpringBoot");
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher book count: " + publisher.getBooks().size());
    }
}
