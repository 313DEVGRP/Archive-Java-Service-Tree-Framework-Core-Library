package egovframework.com.cop.ems.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.EgovSndngMailRegistService;
import egovframework.com.cop.ems.service.SndngMailVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class EgovSndngMailRegistController {

	@Resource(name = "sndngMailRegistService")
	private EgovSndngMailRegistService sndngMailRegistService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	static final char FILE_SEPARATOR = File.separatorChar;

	@IncludedInfo(name = "메일발송", order = 360, gid = 40)
	@RequestMapping(value = "/cop/ems/insertSndngMailView.do")
	public String insertSndngMailView(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {

		model.addAttribute("resultInfo", sndngMailVO);
		return "egovframework/com/cop/ems/EgovMailRegist";
	}

	@RequestMapping(value = "/cop/ems/insertSndngMail.do")
	public String insertSndngMail(final MultipartHttpServletRequest multiRequest, @ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model, HttpServletRequest request)
			throws Exception {

		String link = "N";
		if (sndngMailVO != null && sndngMailVO.getLink() != null && !sndngMailVO.getLink().equals("")) {
			link = sndngMailVO.getLink();
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

		List<FileVO> _result = new ArrayList<FileVO>();
		String _atchFileId = "";
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			_result = fileUtil.parseFileInf(files, "MSG_", 0, "", "");
			_atchFileId = fileMngService.insertFileInfs(_result); //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.

		}

		String orignlFileList = "";

		for (int i = 0; i < _result.size(); i++) {
			FileVO fileVO = _result.get(i);
			orignlFileList = fileVO.getOrignlFileNm();
		}

		if (sndngMailVO != null) {
			sndngMailVO.setAtchFileId(_atchFileId);
			sndngMailVO.setDsptchPerson(user.getId());
			sndngMailVO.setOrignlFileNm(orignlFileList);
		}

		// 발송메일을 등록한다.
		boolean result = sndngMailRegistService.insertSndngMail(sndngMailVO);
		if (result) {
			if (link.equals("N")) {
				return "redirect:/cop/ems/selectSndngMailList.do";
			} else {
				model.addAttribute("closeYn", "Y");
				return "egovframework/com/cop/ems/EgovMailRegist";
			}
		} else {
			return "egovframework/com/cmm/error/egovError";
		}
	}

	@RequestMapping(value = "/cop/ems/backSndngMailRegist.do")
	public String backSndngMailRegist(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {

		return "redirect:/cop/ems/selectSndngMailList.do";
	}
}