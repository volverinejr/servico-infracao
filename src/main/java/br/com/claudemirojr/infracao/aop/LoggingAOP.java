package br.com.claudemirojr.infracao.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.claudemirojr.infracao.config.Security;

@Aspect
@Component
public class LoggingAOP {

	final static Logger log = LoggerFactory.getLogger(LoggingAOP.class);
	
	
	@Autowired
	private Security security;

	@Pointcut(value = "execution(* br.com.claudemirojr.infracao.controller.*.findBy*(..) )")
	public void myPointcut() {

	}

	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();

		String className = pjp.getTarget().getClass().toString();
		String methodName = pjp.getSignature().getName();
		Object[] array = pjp.getArgs();
		String argumento = mapper.writeValueAsString(array);

		Object object = pjp.proceed();

		String retorno = mapper.writeValueAsString(object);
		
		LogPesquisa logPesquisa = new LogPesquisa(this.getUsuarioLogado(), className, methodName, argumento, retorno);
		log.info( logPesquisa.toString() );

		return object;

	}
	
	
	
	private String getUsuarioLogado() {
		return security.getUsuarioLogado();
	}

}
