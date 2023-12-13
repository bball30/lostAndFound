package ru.itmo.lostandfound.photo;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.waiters.WaiterParameters;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itmo.lostandfound.exceptions.ObjectStorageException;
import ru.itmo.lostandfound.repositories.PhotoRepository;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;

/**
 * Сервис для работы с Amazon Web Services (AWS)
 *
 * @author Andryss
 */
@Component
@RequiredArgsConstructor
public class AwsUtil {

    private final PhotoRepository photoRepository;

    private AmazonS3 amazonS3;

    @Autowired(required = false)
    public void setAmazonS3(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    /**
     * Название bucket'а, в который будет производиться сохранение файлов
     */
    @Value("${aws.bucket-name}")
    private String bucketName;

    /**
     * Время доступности ссылки на чтение данных из хранилища
     */
    @Value("${aws.expiration-time-millis}")
    private Long expirationTimeMillis;

    /**
     * Создание/получение bucket'а в S3 хранилище
     */
    @PostConstruct
    private void createBucket() {
        if (amazonS3 != null && !amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket(new CreateBucketRequest(bucketName));
            amazonS3.waiters().bucketExists().run(new WaiterParameters<>(new HeadBucketRequest(bucketName)));
        }
    }

    /**
     * Загрузка фотографии в S3 хранилище
     *
     * @param key идентификатор загружаемого объекта
     * @param file загружаемый файл
     * @throws IOException в случае ошибок доступа к файлу
     * @throws ObjectStorageException в случае проблем с доступом к <code>amazonS3 API</code>
     */
    public void uploadPhoto(String key, MultipartFile file) throws IOException {
        if (amazonS3 == null) {
            throw new ObjectStorageException("object storage");
        }
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        amazonS3.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), metadata));
        amazonS3.waiters().objectExists().run(new WaiterParameters<>(new GetObjectMetadataRequest(bucketName, key)));
    }

    /**
     * Получение ссылки на файл из хранилища
     *
     * @param key идентификатор файла
     * @return URL на получение файла
     * @throws ObjectStorageException в случае проблем с доступом к <code>amazonS3 API</code>
     */
    public URL downloadPhotoUrl(String key) {
        if (amazonS3 == null) {
            throw new ObjectStorageException("object storage");
        }
        Date expirationDate = Date.from(Instant.now().plusMillis(expirationTimeMillis));
        return amazonS3.generatePresignedUrl(bucketName, key, expirationDate, HttpMethod.GET);
    }
}
