package google.drive.domain;

import google.drive.IndexerApplication;
import google.drive.domain.Indexed;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Index_table")
@Data
public class Index {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<String> keywords;

    private String fileId;

    @PostPersist
    public void onPostPersist() {
        Indexed indexed = new Indexed(this);
        indexed.publishAfterCommit();
    }

    public static IndexRepository repository() {
        IndexRepository indexRepository = IndexerApplication.applicationContext.getBean(
            IndexRepository.class
        );
        return indexRepository;
    }

    public static void makeIndex(FileUploaded fileUploaded) {
        /** Example 1:  new item  */
        Index index = new Index();

        index.setFileId(String.valueOf(fileUploaded.getId())); //anti-corruption -- 통역자 
        index.setKeywords(Arrays.asList(fileUploaded.getName().split(" "))); 

        repository().save(index);


        Indexed indexed = new Indexed(index);
        indexed.publishAfterCommit();
       

        /** Example 2:  finding and process
        
        repository().findById(fileUploaded.get???()).ifPresent(index->{
            
            index // do something
            repository().save(index);

            Indexed indexed = new Indexed(index);
            indexed.publishAfterCommit();

         });
        */

    }
}
