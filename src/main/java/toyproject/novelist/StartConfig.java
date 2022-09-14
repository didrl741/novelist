package toyproject.novelist;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.domain.word.Words;
import toyproject.novelist.service.TodayWordsService;
import toyproject.novelist.service.WordsService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class StartConfig implements CommandLineRunner {

    private final WordsService wordsService;
    private final TodayWordsService todayWordsService;

    private static final String EXT_XLS = "XLS";
    private static final String EXT_XLSX = "XLSX";

    Words words = new Words();

    // 서버가 시작될 때 생성
    @Override
    public void run(String... args) {

        //Words words = new Words();

        // 현재 나의 로컬 절대경로로 설정. (상대경로로 바꿔야 함) + (배포시 또 수정사항 있음)
        String path = "C:\\Users\\didrl\\OneDrive - 홍익대학교\\바탕 화면\\novelist\\src\\main\\resources\\static\\data\\words.xlsx";
        File file = new File(path);

        if( file.exists() ) {
            System.out.println( "파일존재 확인!" );
        }

        try {
            readExcel(path);
        } catch (IOException e) {
            e.printStackTrace();
        }



        wordsService.join(words);

        // 랜덤으로 5단어 가져와서 TodayWords 엔티티 생성 (중복 문제 해결)
        TodayWords todayWords = new TodayWords(words.makeRandomWords(), LocalDate.now());

        todayWordsService.save(todayWords);

    }

    public  List<Map<String, String>> readExcel(String filePath ) throws IOException {

        File file = new File(filePath);

        // 확장자
        String ext = FilenameUtils.getExtension( file.getName() );

        List<Map<String, String>> resultMap = null;

        if( EXT_XLS.equalsIgnoreCase( ext ) ) {
            // resultMap = readExcelForXls(filePath);
        }
        else if( EXT_XLSX.equalsIgnoreCase( ext ) ) {
            resultMap = readExcelForXlsx(filePath);
        }

        return resultMap;

    }

    @SuppressWarnings("resource")
    private  List<Map<String, String>> readExcelForXlsx( String filePath ) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();
        System.out.println("rows ====== " + rows);

        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

        for( int rowindex = 0; rowindex < rows; rowindex++ ) {

            // XSSFRow row = sheet.getRow(rowindex);
            Row row = sheet.getRow(rowindex);

            if( row == null ) {
                break;
                //continue;
            }

            int cells = row.getPhysicalNumberOfCells();

            Map<String, String> resultMap = new HashMap<String, String>();

            for( int columnindex = 0; columnindex <= cells; columnindex++ ) {

                // XSSFCell cell = row.getCell(columnindex);
                Cell cell = row.getCell(columnindex);

                String value = "";

                if( cell == null ) {
                    continue;
                }
                else {

                    switch (cell.getCellType()) {

                        case STRING:
                            value = cell.getStringCellValue();
                            break;

                        case BLANK:
                            value = String.valueOf(cell.getBooleanCellValue());
                            break;

                        case ERROR:
                            value = String.valueOf(cell.getErrorCellValue());

                    }

                }

                resultMap.put( String.valueOf( columnindex ) , value );

                words.addWord(value);
                System.out.println("value ====> " + value);
            }

            resultList.add(resultMap);

        }

        return resultList;
    }
}
