package com.books.LiterAlura.service;

public interface ConverterInterface {
    <T> T getData(String json, Class<T> cls);
}
