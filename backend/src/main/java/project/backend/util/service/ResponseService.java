package project.backend.util.service;

import org.springframework.stereotype.Service;
import project.backend.util.dto.CommonResponse;
import project.backend.util.dto.CommonResult;
import project.backend.util.dto.ListResult;
import project.backend.util.dto.SingleResult;

import java.util.List;

@Service
public class ResponseService {

    //단일 결과
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    //복수 결과
    public <T> ListResult<T> getListResult(List<T> data) {
        ListResult<T> result = new ListResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    //성공 결과
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    //실패 결과
    public CommonResult getFailResult(int code, String message) {
        CommonResult result  = new CommonResult();
        setFailResult(result, code, message);
        return result;
    }

    //성공 결과 매핑
    public void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(CommonResponse.SUCCESS.getMessage());
    }

    //실패 결과 매핑
    public void setFailResult(CommonResult result, int code, String message) {
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
    }
}
