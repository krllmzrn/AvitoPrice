package com.example.avito.models;


import java.util.HashMap;

public class Microcategory_tree {

     private HashMap<String, String[]> microcategory_tree = new HashMap<>();

    public Microcategory_tree() {
        microcategory_tree.put("Бытовая электроника", new String[]{"Товары для компьютера", "Фототехника", "Телефоны", "Планшеты и электронные книги", "Оргтехника и расходники", "Ноутбуки", "Настольные компьютеры", "Игры, приставки и программы", "Аудио и видео"});
        microcategory_tree.put( "Готовый бизнес и оборудование", new String[]{"Готовый бизнес", "Оборудование для бизнеса"});
        microcategory_tree.put("Для дома и дачи", new String[] {"Мебель и интерьер", "Ремонт и строительство", "Продукты питания", "Растения", "Бытовая техника", "Посуда и товары для кухни"});
        microcategory_tree.put("Животные", new String[]{"Другие животные", "Товары для животных", "Птицы", "Аквариум", "Кошки", "Собаки"});
        microcategory_tree.put("Личные вещи", new String[]{"Детская одежда и обувь", "Одежда, обувь, аксессуары", "Товары для детей и игрушки", "Часы и украшения", "Красота и здоровье"});
        microcategory_tree.put("Недвижимость", new String[]{"Недвижимость за рубежом", "Квартиры", "Коммерческая недвижимость", "Гаражи и машиноместа", "Земельные участки", "Дома, дачи, коттеджи", "Комнаты"});
        microcategory_tree.put("Работа", new String[]{"Резюме", "Вакансии"});
        microcategory_tree.put( "Транспорт", new String[]  {"Автомобили", "Запчасти и аксессуары", "Грузовики и спецтехника", "Водный транспорт", "Мотоциклы и мототехника"});
        microcategory_tree.put("Услуги", new String[]{"Предложения услуг"});
        microcategory_tree.put("Хобби и отдых", new String[]  {"Охота и рыбалка", "Спорт и отдых", "Коллекционирование", "Книги и журналы", "Велосипеды", "Музыкальные инструменты", "Билеты и путешествия"});
    }

    public HashMap<String, String[]> getMicrocategoryTree(){
        return microcategory_tree;
    }

}

