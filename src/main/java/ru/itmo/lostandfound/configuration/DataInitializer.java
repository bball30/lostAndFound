package ru.itmo.lostandfound.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.lostandfound.model.*;
import ru.itmo.lostandfound.repositories.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final CampusRepository campusRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final PhotoRepository photoRepository;
    private final LossPlaceRepository lossPlaceRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {

        Campus kronva = new Campus(null, "Кронверкский",
                "KRN", "Кронверкский проспект, д.49", "09:00 – 21:00", "пн-сб");
        Campus lomo = new Campus(null, "Ломоносова",
                "LOM", "Ломоносова улица д.9", "09:00 – 21:00", "пн-сб");
        Campus birga = new Campus(null, "Биржа",
                "BIR", "Биржевая линия д. 14-16", "09:00 – 21:00", "пн-пт");
        campusRepository.saveAll(List.of(kronva, lomo, birga));

        User adminUser = new User(null, "admin", "admin@yandex.ru",
                passwordEncoder.encode("admin"), Role.ADMIN, birga);
        User holderUser = new User(null, "holder", "holder@yandex.ru",
                passwordEncoder.encode("holder"), Role.HOLDER, kronva);
        User userUser = new User(null, "user", "user@yandex.ru",
                passwordEncoder.encode("user"), Role.USER, kronva);
        userRepository.saveAll(List.of(adminUser, holderUser, userUser));

        LossPlace confPlace = new LossPlace(null, "Конференц-зал");
        LossPlace coffePoint = new LossPlace(null, "Кофепоинт");
        LossPlace dinningRoom = new LossPlace(null, "Столовая");
        LossPlace workingPlace = new LossPlace(null, "Гардероб");
        LossPlace wc = new LossPlace(null, "Туалет");
        LossPlace hallway = new LossPlace(null, "Коридор");
        LossPlace yard = new LossPlace(null, "Двор");
        LossPlace coworking = new LossPlace(null, "Коворкинг");
        LossPlace library = new LossPlace(null, "Библиотека");
        LossPlace stairs = new LossPlace(null, "Лестница");
        LossPlace gym = new LossPlace(null, "Спортивный зал");
        LossPlace otherLossPlace = new LossPlace(null, "Неизвестно");
        lossPlaceRepository.saveAll(List.of(confPlace, coffePoint, dinningRoom, workingPlace,
                wc, hallway, yard, coworking, library, stairs, gym, otherLossPlace));

        Category headPhones = new Category(null, "Наушники");
        Category laptop = new Category(null, "Ноутбук");
        Category telephone = new Category(null, "Телефон");
        Category charge = new Category(null, "Зарядка");
        Category phoneCase = new Category(null, "Чехол");
        Category clothes = new Category(null, "Одежда");
        Category shoes = new Category(null, "Обувь");
        Category hat = new Category(null, "Шапка");
        Category bag = new Category(null, "Сумка");
        Category key = new Category(null, "Ключи");
        Category umbrella = new Category(null, "Зонт");
        Category glasses = new Category(null, "Очки");
        Category otherCategory = new Category(null, "Другое");
        categoryRepository.saveAll(List.of(headPhones, laptop, telephone, charge, phoneCase, clothes,
                shoes, hat, bag, key, umbrella, glasses, otherCategory));

        Photo firstPhoto = new Photo("01599e7f-3897-4bef-8a6a-0b2434ec7191", Timestamp.valueOf(LocalDateTime.now()), userUser, null);
        Photo secondPhoto = new Photo("3796748c-f8f6-4869-b69f-9585b3d56a50", Timestamp.valueOf(LocalDateTime.now()), userUser, null);
        photoRepository.saveAllAndFlush(List.of(firstPhoto, secondPhoto));

        Item firstPoterjashka = new Item(null, "KRN-23-559", ItemStatus.ACTIVE, firstPhoto, kronva,
                Timestamp.valueOf(LocalDateTime.now()), otherCategory, coworking);
        firstPhoto.setItem(firstPoterjashka);
        Item secondPoterjashka = new Item(null, "LOM-23-112", ItemStatus.LOCKED, secondPhoto, lomo,
                Timestamp.valueOf(LocalDateTime.now()), headPhones, otherLossPlace);
        secondPhoto.setItem(secondPoterjashka);
        itemRepository.saveAll(List.of(firstPoterjashka, secondPoterjashka));

        firstPhoto.setItem(firstPoterjashka);
        secondPhoto.setItem(secondPoterjashka);
        photoRepository.saveAll(List.of(firstPhoto, secondPhoto));
    }
}
