package ohcna;

import ohcna.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserlistViewHandler {


    @Autowired
    private UserlistRepository userlistRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserCreated_then_CREATE_1 (@Payload UserCreated userCreated) {
        try {
            if (userCreated.isMe()) {
                // view 객체 생성
                Userlist userlist = new Userlist();
                // view 객체에 이벤트의 Value 를 set 함
                userlist.setUserId(userCreated.getUserId());
                userlist.setUserName(userCreated.getUserName());
                // view 레파지 토리에 save
                userlistRepository.save(userlist);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserChanged_then_UPDATE_1(@Payload UserChanged userChanged) {
        try {
            if (userChanged.isMe()) {
                // view 객체 조회
                List<Userlist> userlistList = userlistRepository.findByUserId(userChanged.getUserId());
                for(Userlist userlist : userlistList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    userlist.setUserName(userChanged.getUserName());
                    // view 레파지 토리에 save
                    userlistRepository.save(userlist);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenUserCanceled_then_DELETE_1(@Payload UserCanceled userCanceled) {
        try {
            if (userCanceled.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                userlistRepository.deleteByUserId(userCanceled.getUserId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}