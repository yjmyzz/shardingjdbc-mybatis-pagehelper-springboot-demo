package com.cnblogs.yjmyzz.sharding.jdbc.demo.utils;


import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public final class NetworkUtils {
    public NetworkUtils() {
    }

    public static String getLocalHostAddress() {
        InetAddress addr = null;

        try {
            addr = getLocalHostLANAddress();
        } catch (UnknownHostException var2) {
            return "";
        }

        return addr != null ? addr.getHostAddress() : "";
    }

    public static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            Enumeration ifaces = NetworkInterface.getNetworkInterfaces();

            while (ifaces.hasMoreElements()) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                Enumeration inetAddrs = iface.getInetAddresses();

                while (inetAddrs.hasMoreElements()) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            return inetAddr;
                        }

                        if (candidateAddress == null) {
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }

            if (candidateAddress != null) {
                return candidateAddress;
            } else {
                InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
                if (jdkSuppliedAddress == null) {
                    throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
                } else {
                    return jdkSuppliedAddress;
                }
            }
        } catch (Exception var5) {
            UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + var5);
            unknownHostException.initCause(var5);
            throw unknownHostException;
        }
    }

    public static BigInteger ipAddressToBigInt(String ipInString) {
        ipInString = ipInString.replace(" ", "");
        byte[] bytes;
        if (ipInString.contains(":")) {
            bytes = ipv6ToBytes(ipInString);
        } else {
            bytes = ipv4ToBytes(ipInString);
        }

        return new BigInteger(bytes);
    }

    public static String bigIntToIpAddress(BigInteger ipInBigInt) {
        byte[] bytes = ipInBigInt.toByteArray();
        byte[] unsignedBytes = bytes;

        try {
            String ip = InetAddress.getByAddress(unsignedBytes).toString();
            return ip.substring(ip.indexOf(47) + 1).trim();
        } catch (UnknownHostException var4) {
            throw new RuntimeException(var4);
        }
    }

    private static byte[] ipv6ToBytes(String ipv6) {
        byte[] ret = new byte[17];
        ret[0] = 0;
        int ib = 16;
        boolean comFlag = false;
        if (ipv6.startsWith(":")) {
            ipv6 = ipv6.substring(1);
        }

        String[] groups = ipv6.split(":");

        for (int ig = groups.length - 1; ig > -1; --ig) {
            if (groups[ig].contains(".")) {
                byte[] temp = ipv4ToBytes(groups[ig]);
                ret[ib--] = temp[4];
                ret[ib--] = temp[3];
                ret[ib--] = temp[2];
                ret[ib--] = temp[1];
                comFlag = true;
            } else {
                int temp;
                if ("".equals(groups[ig])) {
                    for (temp = 9 - (groups.length + (comFlag ? 1 : 0)); temp-- > 0; ret[ib--] = 0) {
                        ret[ib--] = 0;
                    }
                } else {
                    temp = Integer.parseInt(groups[ig], 16);
                    ret[ib--] = (byte) temp;
                    ret[ib--] = (byte) (temp >> 8);
                }
            }
        }

        return ret;
    }

    private static byte[] ipv4ToBytes(String ipv4) {
        byte[] ret = new byte[5];
        ret[0] = 0;
        int position1 = ipv4.indexOf(".");
        int position2 = ipv4.indexOf(".", position1 + 1);
        int position3 = ipv4.indexOf(".", position2 + 1);
        ret[1] = (byte) Integer.parseInt(ipv4.substring(0, position1));
        ret[2] = (byte) Integer.parseInt(ipv4.substring(position1 + 1, position2));
        ret[3] = (byte) Integer.parseInt(ipv4.substring(position2 + 1, position3));
        ret[4] = (byte) Integer.parseInt(ipv4.substring(position3 + 1));
        return ret;
    }

    public static boolean ipCheck(String tip, String[][] myRange) {
        boolean flag = false;
        BigInteger tbig = ipAddressToBigInt(tip);
        int rangeLength = myRange.length;

        for (int i = 0; i < rangeLength; ++i) {
            for (int j = 0; j < myRange[i].length; ++j) {
                BigInteger sbig = ipAddressToBigInt(myRange[i][j]);
                ++j;
                BigInteger ebig = ipAddressToBigInt(myRange[i][j]);
                if (tbig.compareTo(sbig) == 0) {
                    flag = true;
                    break;
                }

                if (tbig.compareTo(sbig) == 1 && tbig.compareTo(ebig) == -1) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    class IpRange {
        private String[][] ipRange;

        public IpRange(String[][] ip) {
            this.ipRange = ip;
        }

        public String getIpAt(int row, int column) {
            return this.ipRange[row][column];
        }
    }
}