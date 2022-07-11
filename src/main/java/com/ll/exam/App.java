package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public void run() {
        System.out.println("== 명연 SSG ==");

        Scanner sc = new Scanner(System.in);

        List<WiseSaying> wiseSayings = new ArrayList<>();
        int wiseSayingLastId = 0;

        outer:
        while(true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);

            switch(rq.getPath()) {
                case "등록" :
                    System.out.printf("명언 : ");
                    String content = sc.nextLine().trim();
                    System.out.printf("작가 : ");
                    String author = sc.nextLine().trim();
                    int id = ++wiseSayingLastId; // 명언 글 번호 증가

                    WiseSaying wiseSaying = new WiseSaying(id, content, author);
                    wiseSayings.add(wiseSaying);

                    System.out.printf("%d번 명언이 등록되었습니다.\n", id);
                    break;
                case "삭제" :
                    // URL에 입력된 id 얻기
                    int paramId = rq.getIntParam("id", 0);

                    // URL에 입력된 id가 없다면 작업중지
                    if(paramId == 0) {
                        System.out.println("id를 입력해주세요.");
                        continue;
                    }

                    // URL에 입력된 id에 해당하는 명언객체 찾기
                    WiseSaying wiseSaying__ = null;

                    for(WiseSaying wiseSaying___ : wiseSayings) {
                        if(wiseSaying___.id == paramId) {
                            wiseSaying__ = wiseSaying___;
                        }
                    }

                    // 찾지 못했다면 중지
                    if(wiseSaying__ == null) {
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
                        continue;
                    }

                    // 입력된 id에 해당하는 명언객체를 리스트에서 삭제
                    wiseSayings.remove(wiseSaying__);

                    System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
                    break;
                case "목록" :
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("---------------");
                    for(int i = wiseSayings.size() - 1; i >= 0; i--) {
                        WiseSaying wiseSaying_ = wiseSayings.get(i);
                        System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
                    }
                    break;
                case "종료" :
                    System.out.println("SSG 명령이 종료되었습니다.");
                    break outer;
            }
        }

        sc.close();
    }

}
