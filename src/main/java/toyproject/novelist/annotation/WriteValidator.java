package toyproject.novelist.annotation;


import lombok.RequiredArgsConstructor;
import toyproject.novelist.service.TodayWordsService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class WriteValidator implements ConstraintValidator<WriteLimit, String> {

    private final TodayWordsService todayWordsService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.length() < 20) {
            return false;
        }

        // 오늘의 단어들이 포함됐는지 확인 (TodayWordsService를 주입받는거보다 더 좋은 방법이 있을 것 같다.)
        // 예를들면 어노테이션 인자로 배열만 넘긴다던가..
        String[] wordFive = todayWordsService.findTodayWords().makeArr();

        for (int i = 0; i < 5; i++) {
            if (!value.contains(wordFive[i])) {
                return false;
            }
        }
        return true;
    }
}