package ru.itmo.lostandfound.util.resolvers;

/**
 * Интерфейс для получения из базы данных сущностей класса Т по ключу К
 *
 * @param <K> Класс ключа, по которому будет производиться поиск
 * @param <T> Тип возвращаемой сущности
 */
public interface ResolveService<K, T> {
    T resolve(K key);
}
