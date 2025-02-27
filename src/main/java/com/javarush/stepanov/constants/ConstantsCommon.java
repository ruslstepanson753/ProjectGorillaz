package com.javarush.stepanov.constants;

import lombok.experimental.UtilityClass;
import java.io.File;

@UtilityClass
public class ConstantsCommon {

    public static final String SUPPRESSWARNINGS_SET_UNUSED = "unused";

    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_NANOSPRING_IN_FINDIMPLIMENT = "Not found impl for %s (type=%s)";
    public static final String ERROR_NANOSPRING_IN_SCAN_PACKAGES = "Error in scan packages in Nanospring";
    public static final String ERROR_NO_ARGS = "login or password is empty";
    public static final String ERROR_USER_EXIST = "such user already exists";
    public static final String ERROR_PASSWORD_OR_LOGIN_INCORRECT = "login or password is incorrect";
    public static final String ERROR_MAP_TO_JSON = "Ошибка при преобразовании Map в JSON";
    public static final String ERROR_JSON_TO_MAP = "Ошибка при преобразовании JSON в Map";
    public static final String ERROR_BASEREPOSITORY_FIND = "error in Baserepository in metod Find";
    public static final String ERROR_BASEREPOSITORY_GET = "not found Entity with id ";
    public static final String ERROR_BASEREPOSITORY_CREATE = "error while creating entity";
    public static final String ERROR_BASEREPOSITORY_UPDATE = "error while updating entity";
    public static final String ERROR_BASEREPOSITORY_DELETE = "error while deleting entity";
    public static final String ERROR_QUIZSERVICE_READ_FILE = "error read file in Quizservice";
    public static final String ERROR_CLEANER_REQ_NAME_GET = "GET";
    public static final String ERROR_REQ_SET_ATR = "error when attr set to Req";

    public static final String ATTRIBUTE_LOGIN = "login" ;

    public static final String IMG_PATH = "images" + File.separator;
    public static final String IMG_START_QUEST = IMG_PATH + "start_quest.jpg";
    public static final String IMG_START_ROULETTE = IMG_PATH + "start_roulette.jpg";
    public static final String IMG_START_QUIZ = IMG_PATH + "start_quiz.jpg";
    public static final String IMG_START_HEAD = IMG_PATH + "start_head.jpg";

    public static final String AUDIO_PATH = "audio" + File.separator;
    public static final String AUDIO_START_FOOTER = AUDIO_PATH + "Vesennijj_Leningrad.mp3";

    public static final String GO_EMPTY_ADRESS = "";
    public static final String GO_HOME = "/home";
    public static final String GO_START = "/start-page";
    public static final String GO_QUEST = "/game-quest";
    public static final String GO_QUIZ = "/game-quiz";
    public static final String GO_ROULETTE = "/game-roulette";
    public static final String GO_LOGIN_ENTRANCE = "/login-entrance";
    public static final String GO_LOGIN_REGISTRATION = "/login-registration";
    public static final String GO_STATISTIC = "/statistic";

    public static final int QUEST_END_STEP = 6;
    public static final int QUEST_MIN_RESOURCE = 0;
    public static final int QUEST_LOSS_STEP = 2;

    public static int QUIZSERVICE_NUMBER_OF_QUESTIONS = 5;
    public static String QUIZSERVICE_TEXT_FILE_NAME = "questions.txt";
    public static final int QUIZSERVICE_FIRST_STEP = 0;
    public static final String QUIZSERVICE_TRUE_ANSWERS = " Верных ответов " ;
    public static final String QUIZSERVICE_FOR_ANSWER = " На вопрос:  " ;
    public static final String QUIZSERVICE_OF_ = " из  " ;
    public static final String QUIZSERVICE_ENTERED_WRONG_ANSWER = "Получен неверный ответ: " ;
    public static final String QUIZSERVICE_TRUE_ANSWER = "Верный ответ: " ;
    public static final String QUIZSERVICE_NEXT_LINE = "\n" ;
    public static final String QUIZSERVICE_DOUBLE_NEXT_LINE = "\n\n" ;

    public final static String ROLETTESERVICE_RED = "RED";
    public final static String ROLETTESERVICE_BLACK = "BLACK";
    public final static String ROLETTESERVICE_ZERO = "ZERO";
    public final static String ROLETTESERVICE_MAP_RESULT_WIN = "RESULT_WIN";
    public final static String ROLETTESERVICE_MAP_RESULT_LOSS = "RESULT_LOSS";
    public final static String ROLETTESERVICE_MAP_START_DESCRIPTION = "START_DESCRIPTION";
    public final static String ROLETTESERVICE_MAP_RED_BUTTON_DESCRIPTION = "RED_BUTTON_DESCRIPTION";
    public final static String ROLETTESERVICE_MAP_BLACK_BUTTON_DESCRIPTION = "BLACK_BUTTON_DESCRIPTION";
    public final static String ROLETTESERVICE_MAP_ZERO_BUTTON_DESCRIPTION = "ZERO_BUTTON_DESCRIPTION";
    public final static String ROLETTESERVICE_MAP_IMAGE_URL_START = "IMAGE_URL_START";
    public final static String ROLETTESERVICE_MAP_IMAGE_URL_ = "IMAGE_URL_";
    public final static String ROLETTESERVICE_RESULT_COLOR_ = "RESULT_COLOR_";

    public final static String EMPTY_LINE = "";

    public final static String GAME_QUEST_NAME = "game-quest";
    public final static String GAME_ROULETTE_NAME = "game-roulette";
    public final static String GAME_QUIZ_NAME = "game-quiz";

    public final static String ATTR_USER = "user";
    public final static String ATTR_LOGIN = "login";
    public final static String ATTR_GAMES_COUNT = "gamescount";
    public final static String ATTR_WINS_COUNT = "winscount";
    public final static String ATTR_LOSS_COUNT = "losscount";

    public final static String ATTR_IMG_EVIDENCE = "IMAGE_URL_EVIDENCE";
    public final static String ATTR_IMG_GOLD = "IMAGE_URL_GOLD";
    public final static String ATTR_IMG_TIME = "IMAGE_URL_TIME";
    public final static String GAME_QUEST_ATTRIBUTE_BUTTON_LEFT = "buttonLeft";
    public final static String GAME_QUEST_ATTRIBUTE_BUTTON_RIGHT = "buttonRight";
    public final static String GAME_QUEST_ATTRIBUTE_RESULT = "result";
    public final static String GAME_QUEST_ATTRIBUTE_RESULT_RIGHT = "resultRight";
    public final static String GAME_QUEST_ATTRIBUTE_RESULT_LEFT = "resultLeft";
    public final static String GAME_QUEST_ATTRIBUTE_DESCRIPTION = "description";
    public final static String GAME_QUEST_ATTRIBUTE_TIME = "time";
    public final static String GAME_QUEST_ATTRIBUTE_EVIDENCE = "evidence";
    public final static String GAME_QUEST_ATTRIBUTE_GOLD = "gold";
    public final static String GAME_QUEST_ATTRIBUTE_IMG_URL = "imageUrl";
    public final static String GAME_QUEST_ATTRIBUTE_IS_WIN = "isWin";
    public final static String GAME_QUEST_ATTRIBUTE_IS_LOSS = "isLoss";
    public final static String GAME_QUEST_ATTRIBUTE_LOSS_CAUSE = "lossСause";
    public final static String ATTR_PICKED_BUTTON = "pickedButton";

    public final static String GAME_QUIZ_ATTRIBUTE_PICKED_BUTTON = "pickedButton";
    public final static String GAME_QUIZ_ATTRIBUTE_ANSWER = "answer";
    public final static String GAME_QUIZ_ATTRIBUTE_DESCRIPTION = "description";
    public final static String GAME_QUIZ_ATTRIBUTE_QUESTION_NUMBER = "questionNumber";
    public final static String GAME_QUIZ_ATTRIBUTE_IS_DONE = "isDone";

    public final static String GAME_ROULETTE_ATTRIBUTE_PICKED_BUTTON = "pickedButton";
    public final static String GAME_ROULETTE_MAP_START_DESCRIPTION = "START_DESCRIPTION";
    public final static String GAME_ROULETTE_MAP_RED_BUTTON_DESCRIPTION = "RED_BUTTON_DESCRIPTION";
    public final static String GAME_ROULETTE_MAP_BLACK_BUTTON_DESCRIPTION = "BLACK_BUTTON_DESCRIPTION";
    public final static String GAME_ROULETTE_MAP_ZERO_BUTTON_DESCRIPTION = "ZERO_BUTTON_DESCRIPTION";
    public final static String GAME_ROULETTE_IMAGE_URL_START = "IMAGE_URL_START";

    public final static String GAME_ATTRIBUTE_IMAGE_URL = "imageUrl";
    public final static String GAME_ATTRIBUTE_RESULT_COLOR = "resultColor";
    public final static String GAME_ATTRIBUTE_WIN_LOSS_DESCRIPTION = "winLossDescription";
    public final static String GAME_ATTRIBUTE_IS_DONE = "isDone";

    public final static String LOGIN_ATTRIBUTE_LOGIN = "login";
    public final static String LOGIN_ATTRIBUTE_PASSWORD = "password";

    public final static String STATISTIC_ATTRIBUTE_QUEST_GAMERS = "questGamers";
    public final static String STATISTIC_ATTRIBUTE_ROULETTE_GAMERS = "rouletteGamers";
    public final static String STATISTIC_ATTRIBUTE_QUIZ_GAMERS = "quizGamers";

    public final static String LIQUBASE_INIT_START_INFO = "Running Liquibase...";
    public final static String LIQUBASE_INIT_END_INFO = "Running Liquibase...DONE";
    public final static String LIQUBASE_COMMAND_COPE_UPDATE = "update";
    public final static String LIQUBASE_COMMAND_UPDATE_ARGNAME_CHANGE_LOG = "changelogFile";
    public final static String LIQUBASE_COMMAND_UPDATE_ARGNAME_URL = "url";
    public final static String LIQUBASE_COMMAND_UPDATE_ARGNAME_USERNAME = "username";
    public final static String LIQUBASE_COMMAND_UPDATE_ARGNAME_PASSWORD = "password";
    public final static String LIQUBASE_URL_CHANGELOG_XML = "db/changelog.xml";

    public final static String DB_URL = "jdbc:postgresql://localhost:5432/game";
    public final static String DB_USERNAME = "postgres";
    public final static String DB_PASSWORD = "postgres";

    public final static String NANO_SPRING_CLASSES_NAME = "classes";
    public final static String NANO_SPRING_CLASS_EXTENSION = ".class";
    public final static String NANO_SPRING_DOT = ".";
    public final static String NANO_SPRING_EMPTY = "";
    public final static String NANO_SPRING_CLASS_NAME = "NanoSpring.class";

    public final static String FRONT_CONTROLLER_WEB_INF_VIEW = "/WEB-INF/";
    public final static String FRONT_CONTROLLER_JSP_EXTENSION = ".jsp";

    public final static String HTTP_RESOLVER_EMPTY_URI = "/";
    public final static String HTTP_RESOLVER_DOT = ".";

    public final static String QUEST_SERVICE_MAP_DESCRIPTION_TEXT_WIN = "DESCRIPTION_TEXT_WIN";
    public final static String QUEST_SERVICE_MAP_IMAGE_URL_WIN = "IMAGE_URL_WIN";
    public final static String QUEST_SERVICE_MAP_DESCRIPTION_TEXT_LOSS = "DESCRIPTION_TEXT_LOSS";
    public final static String QUEST_SERVICE_MAP_IMAGE_URL_LOSS = "IMAGE_URL_LOSS";
    public static final String QUEST_SERVICE_BUTTON_LEFT = "l";
    public static final String QUEST_SERVICE_BUTTON_RIGHT = "r";
    public static final String QUEST_SERVICE_MAP_START_STEP = "START_STEP";
    public static final String QUEST_SERVICE_MAP_START_TIME = "START_TIME";
    public static final String QUEST_SERVICE_MAP_START_EVIDENCE = "START_EVIDENCE";
    public static final String QUEST_SERVICE_MAP_START_GOLD = "START_GOLD";
    public static final String QUEST_SERVICE_MAP_IMAGE_URL_GOLD = "IMAGE_URL_GOLD";
    public static final String QUEST_SERVICE_MAP_IMAGE_URL_TIME = "IMAGE_URL_TIME";
    public static final String QUEST_SERVICE_MAP_CAUSE_TEXT_TIME_LOSS = "CAUSE_TEXT_TIME_LOSS";
    public static final String QUEST_SERVICE_MAP_CAUSE_TEXT_GOLD_LOSS = "CAUSE_TEXT_GOLD_LOSS";
    public static final String QUEST_SERVICE_MAP_CAUSE_TEXT_EVIDENCE_LOSS = "CAUSE_TEXT_EVIDENCE_LOSS";
    public static final String QUEST_SERVICE_MAP_CAUSE_TEXT_WRONG_STEP_LOSS = "CAUSE_TEXT_WRONG_STEP_LOSS";
    public static final String QUEST_SERVICE_MAP_CAUSE_TEXT_UNKNOWN_LOSS = "CAUSE_TEXT_UNKNOWN_LOSS";
}
