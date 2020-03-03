function X = fmp(xi,pX,N)

X = zeros(1,N);
for i = 1:N
    r = rand();                 %valor entre 0 e 1
    j = 1 + sum(r>cumsum(pX));  %index do xi correspondente
    X(i) = xi(j); 
end

