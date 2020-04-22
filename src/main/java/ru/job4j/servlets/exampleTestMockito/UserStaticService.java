package ru.job4j.servlets.exampleTestMockito;

public class UserStaticService {
    public String useStatic(final Object obj) {
        StaticService.doStatic();
        //
        return StaticService.doStaticWithParams(obj);
    }
}