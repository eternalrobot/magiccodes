package Core.db.Service;

import Core.db.Dao.codeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("codesearchPageService")
public class codesearchPageService{
    @Autowired
    codeDao codeDao;


}
